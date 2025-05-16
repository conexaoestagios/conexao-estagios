import { Button } from "../UI/Button";
import { Checkbox } from "../UI/Checkbox";
import { Input } from "../UI/Input";

interface FormRegisterProps {
  variant: "student" | "company";
}

export function FormRegister({ variant }: FormRegisterProps) {
  return (
    <form className="w-screen h-screen flex-1 bg-[#0F2B3E] p-6 rounded-lg">
      <h1 className="text-3xl font-bold text-white mb-6 underline decoration-cyan-500">
        Cadastro
      </h1>

      {variant === "student" && (
        <>
          <Input label="Nome completo" id="nome_completo" placeholder="" />
          <Input label="Nome de usuário" id="nome_usuario" placeholder="" />
          <Input label="Telefone (opcional)" id="telefone" placeholder="" />
          <Input label="CPF" id="cpf" placeholder="" />

          <div className="grid grid-cols-2 gap-4">
            <Input label="Cidade" id="cidade" placeholder="" />
            <Input label="Estado" id="estado" placeholder="" />
          </div>

          <Input label="E-mail" id="email" type="email" placeholder="" />

          <div className="grid grid-cols-2 gap-4">
            <Input label="Senha" id="senha" type="password" placeholder="" />
            <Input
              label="Confirmar senha"
              id="confirmar_senha"
              type="password"
              placeholder=""
            />
          </div>
        </>
      )}

      {variant === "company" && (
        <>
          <div className="grid grid-cols-2 gap-4">
            <Input label="Nome Fantasia" id="nome_fantasia" placeholder="" />
            <Input label="Razão Social" id="razao_social" placeholder="" />
          </div>

          <Input label="CNPJ" id="cnpj" placeholder="" />
          <Input label="Nome de Usuário" id="nome_usuario" placeholder="" />
          <Input label="E-mail" id="email" type="email" placeholder="" />

          <div className="grid grid-cols-2 gap-4">
            <Input label="Senha" id="senha" type="password" placeholder="" />
            <Input
              label="Confirmar senha"
              id="confirmar_senha"
              type="password"
              placeholder=""
            />
          </div>

          <div className="grid grid-cols-2 gap-4">
            <Input label="Cidade" id="cidade" placeholder="" />
            <Input label="Estado" id="estado" placeholder="" />
          </div>
        </>
      )}

      <div className="my-4">
        <Checkbox label="Aceito os termos de uso" />
      </div>

      <Button title="Continuar" variant="continue" onClick={() => {}} />
    </form>
  );
}
