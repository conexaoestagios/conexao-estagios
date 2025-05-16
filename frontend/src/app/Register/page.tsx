"use client";

import { FormRegister } from "@/components/Form/FormRegister";
import { Header } from "@/components/Layout/Header";
import { useSearchParams } from "next/navigation";

const Register = () => {
  const searchParams = useSearchParams();
  const variantParam = searchParams.get("variant") || "student";
  const variant = variantParam === "company" ? "company" : "student";

  return (
    <div className="w-screen h-screen flex">
      <div className="w-1/2 h-full flex items-center justify-center bg-cover bg-center bg-white">
        {variant === "student" ? (
          <Header
            variant="side"
            title="Estagiário"
            subtitle="Busque oportunidades no  mercado de trabalho"
          />
        ) : (
          <Header
            variant="side"
            title="Empresa"
            subtitle="Encontre novos talentos para sua empresa"
          />
        )}
      </div>
      <div className="w-1/2 h-full flex items-center justify-center bg-[#083344]">
        {variant === "company" ? (
          <FormRegister variant="company" />
        ) : (
          <FormRegister variant="student" />
        )}
      </div>
    </div>
  );
};

export default Register;
