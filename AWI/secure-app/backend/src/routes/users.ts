import bcrypt from 'bcryptjs';
import { Router } from 'express';
import pool from '../db/database.js';
import { requireAdmin } from '../middleware/auth-admin.js';

const router = Router();

router.get('/me', async (req, res, next) => {
  if (!req.user) {
    res.status(401).json({ error: 'Authentification requise' });
    return;
  }

  try {
    const { rows } = await pool.query(
      'SELECT id, login, role FROM users WHERE id = $1',
      [req.user.id],
    );
    res.json(rows[0] ?? null);
  } catch (error) {
    next(error);
  }
});

router.get('/', requireAdmin, async (_req, res, next) => {
  try {
    const { rows } = await pool.query(
      'SELECT id, login, role FROM users ORDER BY id',
    );
    res.json(rows);
  } catch (error) {
    next(error);
  }
});

router.post('/', requireAdmin, async (req, res) => {
  const { login, password } = req.body as {
    login?: string;
    password?: string;
  };

  if (!login || !password) {
    return res
      .status(400)
      .json({ error: 'Login et mot de passe requis' });
  }

  try {
    const hash = await bcrypt.hash(password, 10);
    await pool.query(
      'INSERT INTO users (login, password_hash) VALUES ($1, $2)',
      [login, hash],
    );
    res.status(201).json({ message: 'Utilisateur créé' });
  } catch (error: unknown) {
    if (
      typeof error === 'object' &&
      error !== null &&
      'code' in error &&
      (error as { code?: string }).code === '23505'
    ) {
      res.status(409).json({ error: 'Login deja existant' });
    } else {
      console.error(error);
      res.status(500).json({ error: 'Erreur serveur' });
    }
  }
});

export default router;
