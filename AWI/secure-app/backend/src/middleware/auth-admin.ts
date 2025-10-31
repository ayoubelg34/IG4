import type { NextFunction, Request, Response } from 'express';

export function requireAdmin(
  req: Request,
  res: Response,
  next: NextFunction,
): void {
  if (!req.user || req.user.role !== 'admin') {
    res
      .status(403)
      .json({ error: 'Acces reserve aux administrateurs' });
    return;
  }

  next();
}
