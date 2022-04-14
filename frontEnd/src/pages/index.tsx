import { useForm } from "react-hook-form";
import { getAPIClient } from "../services/axios";
import { useContext, useEffect, useState } from "react";
import Oval from "react-loading-icons/dist/components/oval";
import { BotContext } from "../contexts/BotContext";
import Head from "next/head";

function Home() {

    const { register, handleSubmit,reset } = useForm();
    const [clickExecute, setClickExecute] = useState(false);
    const [message, setMessage] = useState("");
    const { execute } = useContext(BotContext);
    useEffect(() => {
        reset({"search":"Iphone 11"});
    },[clickExecute]);

    async function handleSignIn(data) {
        setClickExecute(true);
        execute(data).then((resp) => {
            resp ?
                setMessage("Executado com sucesso")
            :
                setMessage("Erro ao executar")
            setClickExecute(false);
        });

    }


  return (
    <div className="min-h-screen flex items-center justify-center bg-gray-50 py-12 px-4 sm:px-6 lg:px-8">
    <Head>
      <title>Home</title>
    </Head>

    <div className="max-w-sm w-full space-y-8">
      <div>
        <h2 className="mt-6 text-center text-3xl font-extrabold text-gray-900">Execução Bot OLX</h2>
      </div>
      <form className="mt-8 space-y-6" onSubmit={handleSubmit(handleSignIn)}>
        <div className="rounded-md shadow-sm -space-y-px">
        <div className="mt-5 flex flex-col xl:w-12/12 lg:w-12/12 md:w-12/12 w-full">
            <label className="pb-2 text-sm font-bold text-gray-800 dark:text-gray-100">Pesquisa</label>
                <input {...register('search')} type="text" id="search" name="search" required
                  className="border border-gray-300 dark:border-gray-700 pl-3 py-3 shadow-sm rounded text-sm focus:outline-none focus:border-indigo-700 bg-transparent placeholder-gray-500 text-gray-500 dark:text-gray-400" placeholder=""/>
        </div>

        <div className="mt-5 flex flex-col xl:w-12/12 lg:w-12/12 md:w-12/12 w-full">
                        <label htmlFor="Tipo" className="pb-2 text-sm font-bold text-gray-800 dark:text-gray-100">
                            Estado
                        </label>
                <select {...register("state")} required
                    className="border border-gray-300 dark:border-gray-700 pl-3 py-3 shadow-sm rounded text-sm focus:outline-none focus:border-indigo-700 bg-transparent placeholder-gray-500 text-gray-500 dark:text-gray-400"
                        >
                    <option value="">
                        Selecione uma opção
                    </option>
                    <option value="go">
                        Goiás
                    </option>
                </select>
            </div>

            <div className="mt-5 flex flex-col xl:w-12/12 lg:w-12/12 md:w-12/12 w-full">
                        <label htmlFor="Tipo" className="pb-2 text-sm font-bold text-gray-800 dark:text-gray-100">
                            Região
                        </label>
                <select {...register('region')} required
                    className="border border-gray-300 dark:border-gray-700 pl-3 py-3 shadow-sm rounded text-sm focus:outline-none focus:border-indigo-700 bg-transparent placeholder-gray-500 text-gray-500 dark:text-gray-400">
                    <option value="">
                        Selecione uma opção
                    </option>
                    <option value="grande-goiania-e-anapolis">
                        DDD 62 - Grande Goiânia e Anápolis
                    </option>
                </select>
            </div>


          <div className="grid grid-cols-2 gap-4">
            <div className="mt-5 flex flex-col xl:w-12/12 lg:w-12/12 md:w-12/12 w-full">
                <label className="pb-2 text-sm font-bold text-gray-800 dark:text-gray-100">Tempo(Minutos)</label>
                    <input {...register('time')} type="number" id="time" name="time" required
                    className="border border-gray-300 dark:border-gray-700 pl-3 py-3 shadow-sm rounded text-sm focus:outline-none focus:border-indigo-700 bg-transparent placeholder-gray-500 text-gray-500 dark:text-gray-400" placeholder=""/>
            </div>
          </div>
        </div>


        <div className="mt-6">
            <p>{message}</p>
        </div>
        {clickExecute ?
                <div>
                <button
                    style={{background:"#139c26",paddingBottom:"30px"}}
                    type="submit"
                    className="group relative w-full flex justify-center py-2 px-4 border border-transparent text-sm font-medium rounded-md text-white button-green"
                >
                  <span className="absolute left-0 inset-y-0 flex items-center pl-3" style={{paddingLeft:"45%"}}>
                  <Oval />
                  </span>
                </button>
              </div>
                :
                <div>
                <button
                    style={{background:"#139c26"}}
                    type="submit"
                    className="group relative w-full flex justify-center py-2 px-4 border border-transparent text-sm font-medium rounded-md text-white button-green"
                >
                    Executar
                  <span className="absolute left-0 inset-y-0 flex items-center pl-3">
                  </span>
                </button>
              </div>
        }

      </form>
    </div>
  </div>
  )
}

export async function GetServerSideProps(ctx) {
    const apiClient = getAPIClient(ctx);

    return apiClient.get("/api/login").then(() => {
        return {
            props: {}
          }
    }).catch(error => {
        console.log(error);
        return {
            redirect: {
              destination: '/autentication',
              permanent: false,
            }
          }
    });
}

  export default Home
