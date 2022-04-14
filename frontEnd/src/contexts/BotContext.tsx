import { createContext } from "react";

import { api } from "../services/api";

type DataBot = {
  search: string;
  state: string;
  region: string;
  time: string;
}

type BotContextType = {
  execute: (data: DataBot) => Promise<boolean>
}

export const BotContext = createContext({} as BotContextType)

export function BotProvider({ children }) {
  async function execute(data:DataBot) {
    return api.post('/scrape',data).then(() => {
        return true;
    }).catch(error => {
        console.log(error);
        return false;
    });
  }

  return (
    <BotContext.Provider value={{ execute }}>
      {children}
    </BotContext.Provider>
  )
}
