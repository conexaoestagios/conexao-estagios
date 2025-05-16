"use client";

import { useState } from "react";
import { Button } from "../UI/Button";
import { Input } from "../UI/Input";

interface FormRegisterProps {
  variant: "student" | "company";
  onSubmit: (data: any) => void;
}

export function FormGPTRegister({ variant, onSubmit }: FormRegisterProps) {
  const [formData, setFormData] = useState({
    name: "",
    username: "",
    email: "",
    password: "",
    confirmPassword: "",
    city: "",
    state: "",
    cnpj: "",
    corporateName: "",
    fantasyName: "",
    cpf: "",
    phone: "",
    acceptedTerms: false,
  });

  function handleChange(e: React.ChangeEvent<HTMLInputElement>) {
    const { name, value, type, checked } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: type === "checkbox" ? checked : value,
    }));
  }

  function handleSubmit(e: React.FormEvent) {
    e.preventDefault();
    onSubmit(formData);
  }

  return (
    <form
      onSubmit={handleSubmit}
      className=" flex-1 w-screen h-screen bg-[#083344] p-6 rounded-xl max-w-md mx-auto space-y-4 text-white"
    >
      <h2 className="text-2xl font-bold mb-4">
        Cadastro {variant === "student" ? "de Estudante" : "de Empresa"}
      </h2>

      {variant === "student" && (
        <>
          <Input
            label="Nome completo"
            id="name"
            placeholder="Seu nome"
            type="text"
          />
          <Input
            label="Nome de usuário"
            id="username"
            placeholder="ex: joaosilva"
            type="text"
          />
          <Input
            label="Telefone (opcional)"
            id="phone"
            placeholder="(00) 00000-0000"
            type="text"
          />
          <Input
            label="CPF"
            id="cpf"
            placeholder="000.000.000-00"
            type="text"
          />
        </>
      )}

      {variant === "company" && (
        <>
          <Input
            label="Nome Fantasia"
            id="fantasyName"
            placeholder="Ex: Tech Solutions"
            type="text"
          />
          <Input
            label="Razão Social"
            id="corporateName"
            placeholder="Ex: Tech Solutions LTDA"
            type="text"
          />
          <Input
            label="CNPJ"
            id="cnpj"
            placeholder="00.000.000/0000-00"
            type="text"
          />
          <Input
            label="Nome de usuário"
            id="username"
            placeholder="empresa123"
            type="text"
          />
        </>
      )}

      <div className="grid grid-cols-2 gap-4">
        <Input
          label="Cidade"
          id="city"
          placeholder="Digite sua cidade"
          type="text"
        />
        <Input
          label="Estado"
          id="state"
          placeholder="Digite seu estado"
          type="text"
        />
      </div>

      <Input
        label="Email"
        id="email"
        placeholder="seu@email.com"
        type="email"
      />

      <div className="grid grid-cols-2 gap-4">
        <Input
          label="Senha"
          id="password"
          placeholder="Digite sua senha"
          type="password"
        />
        <Input
          label="Confirmar Senha"
          id="confirmPassword"
          placeholder="Confirme sua senha"
          type="password"
        />
      </div>

      <label className="flex items-center gap-2 text-sm mt-2">
        <input
          type="checkbox"
          name="acceptedTerms"
          checked={formData.acceptedTerms}
          onChange={handleChange}
          required
        />
        Aceito os termos de uso
      </label>
      <Button title="Continuar" variant="continue" onClick={() => {}} />
    </form>
  );
}
