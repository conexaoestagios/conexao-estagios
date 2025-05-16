import FormLogin from "@/components/Form/FormLogin";
import { Header } from "@/components/Layout/Header";
import logo from "@/assets/ce-icon.svg";

const Login = () => {
  return (
    <div className="flex h-screen bg-white">
      <Header
        variant="side"
        title="Conexão Estágios"
        subtitle="Conectando talentos ao mundo profissional"
        logoSrc={logo}
      />
      <FormLogin />
    </div>
  );
};

export default Login;
