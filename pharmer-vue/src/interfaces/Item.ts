export interface Item {
  id?: number;
  name: string;
  expirationDate: string;
  soonToExpire?: boolean;
  expired?: boolean;
}