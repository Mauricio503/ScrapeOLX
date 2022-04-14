import 'tailwindcss/tailwind.css'
import '../style/global.css';
import '../style/chat.css';
import { AuthProvider } from '../contexts/AuthContext';
import { BotProvider } from '../contexts/BotContext';


export function MyApp({ Component, pageProps }) {

  return (
        <AuthProvider>
            <BotProvider>
                <Component {...pageProps} />
            </BotProvider>
        </AuthProvider>
  )
}

export default MyApp
