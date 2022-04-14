import { createContext, useState } from "react";
import { setCookie } from 'nookies'

import { api } from "../services/api";

type User = {
  name: string;
  email: string;
  avatar_url: string;
}

type SignInData = {
  email: string;
  password: string;
}

type AuthContextType = {
  user: User;
  signIn: (data: SignInData) => Promise<boolean>
}

export const AuthContext = createContext({} as AuthContextType)

export function AuthProvider({ children }) {
  const [user, setUser] = useState<User | null>(null)

  async function signIn({ email, password }: SignInData) {
    return api.post('/login',{username:email,password}).then(response => {
        setCookie(undefined, 'botolx.token', response.data.token);
        api.defaults.headers['token'] = `Bearer ${response.data.token}`;

        setUser(user)

        return true;
    }).catch(error => {
        console.log(error);
        return false;
    });
  }

  return (
    <AuthContext.Provider value={{ signIn,user }}>
      {children}
    </AuthContext.Provider>
  )
}
