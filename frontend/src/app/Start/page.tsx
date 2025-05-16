"use client";
import { useRouter } from "next/navigation";
import { Header } from "../../components/Layout/Header";
import { Button } from "../../components/UI/Button";
import { useState } from "react";
import logo from "@/assets/ce-icon.svg";

function Start() {
  const [isRegistering, setIsRegistering] = useState(false);
  const router = useRouter();

  const handleCompanyClick = () => {
    router.push("/Register?variant=company"); // Rota para cadastro da empresa
  };

  const handleStudentClick = () => {
    router.push("/Register?variant=student"); // Rota para cadastro do estagiário
  };

  const goToLogin = () => {
    router.push("/Login");
  };

  return (
    <div className="flex flex-col items-center text-center bg-[url('../assets/main-background.png')] bg-cover bg-center h-screen w-full justify-center min-h-screen">
      <div>
        <Header
          variant="center"
          title="Conexão Estágios"
          subtitle="Conectando talentos ao mundo profissional"
          logoSrc={logo}
        />
        <div className="flex gap-6 text-center m-5">
          {!isRegistering ? (
            <>
              <Button
                title="Login"
                subtitle="Já tem cadastro"
                onClick={goToLogin}
                variant="start"
              />
              <Button
                title="Cadastrar"
                subtitle="Crie sua conta"
                onClick={() => setIsRegistering(true)}
                variant="start"
              />
            </>
          ) : (
            <>
              <Button
                title="Empresa"
                subtitle="Contrate talentos"
                onClick={handleCompanyClick}
                variant="start"
              />
              <Button
                title="Estagiário"
                subtitle="Encontre vagas"
                onClick={handleStudentClick}
                variant="start"
              />
            </>
          )}
        </div>
      </div>
    </div>
  );
}

export default Start;
