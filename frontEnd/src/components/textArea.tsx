import { FC } from "react";

interface TextArea {
    type:string;
    name:string;
    id:string;
    label: string;
    placeholder?:string;
  }

  const TextArea: FC<TextArea> = ({ type,name,id, label, placeholder="" }) => {
    return (
        <div className="mt-8 flex flex-col xl:w-3/5 lg:w-1/2 md:w-1/2 w-full">
            <label className="pb-2 text-sm font-bold text-gray-800 dark:text-gray-100">
                {label}
            </label>
            <textarea id={id} name={name} required className="bg-transparent border border-gray-300 dark:border-gray-700 pl-3 py-3 shadow-sm rounded text-sm focus:outline-none focus:border-indigo-700 resize-none placeholder-gray-500 text-gray-500 dark:text-gray-400" placeholder="Let the world know who you are" rows={5} defaultValue={""} />
                        <p className="w-full text-right text-xs pt-1 text-gray-500 dark:text-gray-400">Character Limit: 200</p>
                    </div>
    );
  };

  export default TextArea;
