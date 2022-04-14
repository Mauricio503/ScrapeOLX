import Head from 'next/head'
import { LockClosedIcon, UserIcon } from '@heroicons/react/solid'
import { useForm } from 'react-hook-form'
import { useContext, useState } from 'react';
import { AuthContext } from '../contexts/AuthContext';
import Router from 'next/router';

export default function Autentication() {
  const { register, handleSubmit } = useForm();
  const { signIn } = useContext(AuthContext)
  const [ errorAuthenticated,setErrorAuthenticated ] = useState(false);

  async function handleSignIn(data) {
    const isAuthenticated = await signIn(data)
    if(isAuthenticated){
        Router.push('/');
    }else{
        setErrorAuthenticated(true);
    }

  }

  return (

    <div className="min-h-screen flex items-center justify-center bg-gray-50 py-12 px-4 sm:px-6 lg:px-8">
      <Head>
        <title>Home</title>
      </Head>

      <div className="max-w-sm w-full space-y-8">
        <div>
          <h2 className="mt-6 text-center text-3xl font-extrabold text-gray-900">Login</h2>
        </div>
        <form className="mt-8 space-y-6" onSubmit={handleSubmit(handleSignIn)}>
          <input type="hidden" name="remember" defaultValue="true" />
          <div className="rounded-md shadow-sm -space-y-px">
            <div>
              <label htmlFor="email-address" className="sr-only">
                Usuario
              </label>
              <input
                {...register('email')}
                id="email-address"
                name="email"
                type="text"
                autoComplete="email"
                required
                className="appearance-none rounded-none relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 rounded-t-md focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 focus:z-10 sm:text-sm"
                placeholder="Usuario"
              />
            </div>
            <div>
              <label htmlFor="password" className="sr-only">
                Senha
              </label>
              <input
                {...register('password')}
                id="password"
                name="password"
                type="password"
                autoComplete="current-password"
                required
                className="appearance-none rounded-none relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 rounded-b-md focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 focus:z-10 sm:text-sm"
                placeholder="Senha"
              />
            </div>
          </div>

            {
                errorAuthenticated &&
                <div>
                    <p style={{color:'red',textAlign:"center"}}>Autenticação inválida</p>
                </div>
            }


          <div>
            <button
                style={{background:"#139c26"}}
                type="submit"
                className="group relative w-full flex justify-center py-2 px-4 border border-transparent text-sm font-medium rounded-md text-white button-green"
            >
              <span className="absolute left-0 inset-y-0 flex items-center pl-3">
                <LockClosedIcon className="h-5 w-5" aria-hidden="true" />
              </span>
              Login
            </button>
          </div>
        </form>
      </div>
    </div>
  )
}
