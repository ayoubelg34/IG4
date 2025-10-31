import bcrypt from 'bcryptjs';
import pool from './database.js';

export async function ensureAdmin(): Promise<void> {
  const hash = await bcrypt.hash('admin', 10);
  await pool.query(
    `INSERT INTO users (login, password_hash, role)
     VALUES ('admin', $1, 'admin')
     ON CONFLICT (login) DO NOTHING`,
    [hash],
  );
  console.log('Admin account verified or created');
}
