export interface TokenPayload {
  id: number;
  login: string;
  role: string;
  iat?: number;
  exp?: number;
}
