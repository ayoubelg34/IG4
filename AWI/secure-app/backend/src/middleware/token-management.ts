import type {
  CookieOptions,
  NextFunction,
  Request,
  Response,
} from 'express';
import jwt from 'jsonwebtoken';
import type { JwtPayload, Secret, SignOptions } from 'jsonwebtoken';
import type { StringValue } from 'ms';
import type { TokenPayload } from '../types/token-payload.js';

export type { TokenPayload };

const JWT_SECRET: Secret =
  process.env.JWT_SECRET ?? 'change-me-in-production';
const JWT_REFRESH_SECRET: Secret =
  process.env.JWT_REFRESH_SECRET ?? JWT_SECRET;
const JWT_EXPIRATION: StringValue | number =
  (process.env.JWT_EXPIRATION as StringValue | undefined) ?? '15m';
const REFRESH_EXPIRATION: StringValue | number =
  (process.env.JWT_REFRESH_EXPIRATION as StringValue | undefined) ?? '7d';

const ACCESS_TOKEN_SIGN_OPTIONS: SignOptions = {
  expiresIn: JWT_EXPIRATION,
};

const REFRESH_TOKEN_SIGN_OPTIONS: SignOptions = {
  expiresIn: REFRESH_EXPIRATION,
};

export const ACCESS_TOKEN_MAX_AGE_MS = 15 * 60 * 1000;
export const REFRESH_TOKEN_MAX_AGE_MS = 7 * 24 * 60 * 60 * 1000;

export const ACCESS_TOKEN_COOKIE_OPTIONS: CookieOptions = {
  httpOnly: true,
  secure: true,
  sameSite: 'strict',
  maxAge: ACCESS_TOKEN_MAX_AGE_MS,
  path: '/',
};

export const REFRESH_TOKEN_COOKIE_OPTIONS: CookieOptions = {
  httpOnly: true,
  secure: true,
  sameSite: 'strict',
  maxAge: REFRESH_TOKEN_MAX_AGE_MS,
  path: '/',
};

export function createAccessToken(payload: TokenPayload): string {
  return jwt.sign(payload, JWT_SECRET, ACCESS_TOKEN_SIGN_OPTIONS);
}

export function createRefreshToken(payload: TokenPayload): string {
  return jwt.sign(payload, JWT_REFRESH_SECRET, REFRESH_TOKEN_SIGN_OPTIONS);
}

export function verifyToken(
  req: Request,
  res: Response,
  next: NextFunction,
): void {
  const token = req.cookies?.access_token;
  if (!token) {
    res.status(401).json({ error: 'Token manquant' });
    return;
  }

  const payload = decodeToken(token, JWT_SECRET);
  if (!payload) {
    res.status(403).json({ error: 'Token invalide ou expire' });
    return;
  }

  req.user = payload;
  next();
}

export function verifyRefreshToken(token: string): TokenPayload | null {
  return decodeToken(token, JWT_REFRESH_SECRET);
}

function decodeToken(
  token: string,
  secret: Secret,
): TokenPayload | null {
  try {
    const decoded = jwt.verify(token, secret) as JwtPayload &
      Partial<TokenPayload>;

    if (
      typeof decoded.id === 'number' &&
      typeof decoded.login === 'string' &&
      typeof decoded.role === 'string'
    ) {
      return {
        id: decoded.id,
        login: decoded.login,
        role: decoded.role,
      };
    }
    return null;
  } catch {
    return null;
  }
}
