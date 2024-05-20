export class AppUser {
  id!: number;

  username: string | undefined;
  password: string | undefined;
  confirmPassword: string | undefined;

  email: string | undefined;
  phone: string | undefined;

  credentialsNonExpired: boolean | undefined;
  enabled: boolean | undefined;
  accountNonExpired: boolean | undefined;
  accountNonLocked: boolean | undefined;
  passwordChanged: boolean | undefined;
}
