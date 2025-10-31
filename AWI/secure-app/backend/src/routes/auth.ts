import bcrypt from 'bcryptjs';
import { Router } from 'express';
import pool from '../db/database.js';
import {
  ACCESS_TOKEN_COOKIE_OPTIONS,
  REFRESH_TOKEN_COOKIE_OPTIONS,
  createAccessToken,
  createRefreshToken,
  verifyRefreshToken,
  verifyToken,
  type TokenPayload,
} from '../middleware/token-management.js';

type UserRow = {
  id: number;
  login: string;
  password_hash: string;
  role: string;
};

type UserPublic = {
  id: number;
  login: string;
  role: string;
};

const router = Router();

router.post('/register', async (req, res) => {
  const { login, password } = req.body as {
    login?: string;
    password?: string;
  };

  if (!login || !password) {
    res.status(400).json({ error: 'Champs manquants' });
    return;
  }

  try {
    const hashed = await bcrypt.hash(password, 10);
    const { rows } = await pool.query<UserPublic>(
      `INSERT INTO users (login, password_hash, role)
       VALUES ($1, $2, 'user')
       RETURNING id, login, role`,
      [login, hashed],
    );

    res
      .status(201)
      .json({ message: 'Utilisateur créé', user: rows[0] });
  } catch (error: unknown) {
    if (
      typeof error === 'object' &&
      error !== null &&
      'code' in error &&
      (error as { code?: string }).code === '23505'
    ) {
      res.status(409).json({ error: 'Login déjà utilisé' });
    } else {
      console.error("Erreur lors de l'inscription:", error);
      res.status(500).json({ error: 'Erreur serveur' });
    }
  }
});

router.post('/login', async (req, res) => {
  const { login, password } = req.body as {
    login?: string;
    password?: string;
  };

  if (!login || !password) {
    res.status(400).json({ error: 'Identifiants manquants' });
    return;
  }

  try {
    const { rows } = await pool.query<UserRow>(
      'SELECT id, login, password_hash, role FROM users WHERE login = $1',
      [login],
    );
    const user = rows[0];

    if (!user) {
      res.status(401).json({ error: 'Utilisateur inconnu' });
      return;
    }

    const match = await bcrypt.compare(password, user.password_hash);
    if (!match) {
      res.status(401).json({ error: 'Mot de passe incorrect' });
      return;
    }

    const payload: TokenPayload = {
      id: user.id,
      login: user.login,
      role: user.role,
    };

    const accessToken = createAccessToken(payload);
    const refreshToken = createRefreshToken(payload);

    res.cookie('access_token', accessToken, ACCESS_TOKEN_COOKIE_OPTIONS);
    res.cookie(
      'refresh_token',
      refreshToken,
      REFRESH_TOKEN_COOKIE_OPTIONS,
    );
    res.json({
      message: 'Authentification reussie',
      user: { id: user.id, login: user.login, role: user.role },
    });
  } catch (error) {
    console.error('Erreur lors de la connexion:', error);
    res.status(500).json({ error: 'Erreur serveur' });
  }
});

router.post('/logout', (_req, res) => {
  res.clearCookie('access_token', {
    path: '/',
    sameSite: 'strict',
    secure: true,
    httpOnly: true,
  });
  res.clearCookie('refresh_token', {
    path: '/',
    sameSite: 'strict',
    secure: true,
    httpOnly: true,
  });
  res.json({ message: 'Deconnexion reussie' });
});

router.post('/refresh', (req, res) => {
  const token = req.cookies?.refresh_token;
  if (!token) {
    res.status(401).json({ error: 'Refresh token manquant' });
    return;
  }

  const payload = verifyRefreshToken(token);
  if (!payload) {
    res.status(403).json({ error: 'Refresh token invalide' });
    return;
  }

  const accessToken = createAccessToken(payload);
  res.cookie('access_token', accessToken, ACCESS_TOKEN_COOKIE_OPTIONS);
  res.json({ message: 'Token actualise' });
});

router.get('/whoami', verifyToken, (req, res) => {
  res.json({ user: req.user });
});

export default router;
