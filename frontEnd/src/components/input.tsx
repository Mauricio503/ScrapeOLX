import { FC } from "react";

interface Input {
    type:string;
    name:string;
    id:string;
    label: string;
    placeholder?:string;
    xl:string;
    lg:string;
    md:string;
  }

  const Input: FC<Input> = ({ type,name,id, label, placeholder="",xl,lg,md }) => {
    return (
        <div className={"mt-5 flex flex-col xl:w-"+xl+"/12 lg:w-"+lg+"/12 md:w-"+md+"/12 w-full"}>
            <label className="pb-2 text-sm font-bold text-gray-800 dark:text-gray-100">
                {label}
            </label>
            <input type={type} id={id} name={name} required
                className="border border-gray-300 dark:border-gray-700 pl-3 py-3 shadow-sm rounded text-sm focus:outline-none focus:border-indigo-700 bg-transparent placeholder-gray-500 text-gray-500 dark:text-gray-400" placeholder={placeholder} />
        </div>
    );
  };

  export default Input;
