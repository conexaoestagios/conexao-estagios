"use client";
import { Button } from "../UI/Button";
import { Checkbox } from "../UI/Checkbox";
import { Input } from "../UI/Input";

// Formulário de login
const FormLogin = () => {
  return (
    <div className="w-1/2 flex flex-col justify-center px-16 bg-cyan-950 text-white">
      <div className="max-w-md w-full mx-auto">
        <h2 className="text-3xl font-bold mb-10">Login</h2>

        <form className="flex flex-col gap-6">
          <Input id="user" label="Usuário" placeholder="Digite seu usuário" />
          <Input
            type="password"
            id="password"
            label="Senha"
            placeholder="Digite sua senha"
          />

          <div className="flex justify-between items-center text-sm">
            <Checkbox label="Lembrar" />
            <a
              href="#"
              className="text-cyan-300 hover:text-cyan-100 hover:underline"
            >
              Esqueceu a senha?
            </a>
          </div>

          <Button
            title="Continuar"
            onClick={() => alert("Continuar")}
            variant="continue"
          />
        </form>

        <p className="text-sm mt-8 text-center text-cyan-100">
          Ainda não criou sua conta no Conexão Estágios?{" "}
          <a
            href="#"
            className="font-semibold text-cyan-300 hover:text-white hover:underline"
          >
            Cadastre-se
          </a>
        </p>
      </div>
    </div>
  );
};

export default FormLogin;
