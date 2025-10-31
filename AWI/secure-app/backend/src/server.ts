import fs from 'node:fs';
import https from 'node:https';
import express from 'express';
import cors from 'cors';
import morgan from 'morgan';
import cookieParser from 'cookie-parser';
import publicRouter from './routes/public.js';
import usersRouter from './routes/users.js';
import { ensureAdmin } from './db/initAdmin.js';
import authRouter from './routes/auth.js';
import { verifyToken } from './middleware/token-management.js';
import { requireAdmin } from './middleware/auth-admin.js';
import 'dotenv/config'

const app = express();

await ensureAdmin().catch((error) => {
  console.error('Failed to ensure admin user:', error);
  process.exit(1);
});

app.use((_, res, next) => {
  res.setHeader('X-Content-Type-Options', 'nosniff');
  res.setHeader('X-Frame-Options', 'SAMEORIGIN');
  res.setHeader('Referrer-Policy', 'no-referrer');
  res.setHeader('Cross-Origin-Resource-Policy', 'same-origin');
  res.setHeader('Cross-Origin-Opener-Policy', 'same-origin');
  res.setHeader('Cross-Origin-Embedder-Policy', 'require-corp');
  next();
});

app.use(morgan('dev'));
app.use(express.json());
app.use(cookieParser());

app.use(
  cors({
    origin: 'https://localhost:4200',
    credentials: true,
    methods: ['GET', 'POST', 'PUT', 'DELETE'],
    allowedHeaders: ['Content-Type', 'Authorization'],
  }),
);

app.use('/api/auth', authRouter);
app.use('/api/users', verifyToken, usersRouter);
app.get('/api/admin', verifyToken, requireAdmin, (_req, res) => {
  res.json({ message: 'Bienvenue admin' });
});
app.use('/api/public', publicRouter);

const certsDir = new URL('../../certs/', import.meta.url);
const key = fs.readFileSync(new URL('localhost-key.pem', certsDir));
const cert = fs.readFileSync(new URL('localhost.pem', certsDir));

https.createServer({ key, cert }, app).listen(4000, () => {
  console.log('Serveur API demarre sur https://localhost:4000');
});
