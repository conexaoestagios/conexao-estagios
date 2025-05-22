"use client";
// Importando elementos visuais
import React from "react";
import bgimage from "../../assets/main-background.png";
import ceIcon from "../../assets/ce-icon.svg";
import Image from "next/image";
import Button from "@/components/UI/Button/Button";

// Importando renderização de Estado
import { useState } from "react";

// Importando elemento de navegação
import { useRouter } from "next/navigation";

// Página Inicial/Principal onde tudo inicia
function Home() {
  // Definindo o estado de renderização
  const [isRendering, setRendering] = useState(false);
  // Definindo estado de navegação
  const router = useRouter();

  // Navegar para pagina de login
  const navigateLogin = () => {
    router.push("/Navigate?variant=login");
  };
  // Navegar para pagina de cadastro Empresa
  const navigateCompany = () => {
    router.push("/Navigate?variant=company");
  };
  //Navegar para pagina de cadastro Estudante
  const navigateStudent = () => {
    router.push("/Navigate?variant=student");
  };
  return (
    <div
      className="flex flex-col min-h-screen "
      style={{
        backgroundImage: `url(${bgimage.src})`,
        backgroundSize: "cover",
        backgroundPosition: "center",
        height: "auto",
        width: "100%",
      }}
    >
      <main className="flex flex-col items-center justify-center h-screen">
        <div className="flex flex-col">
          <div className="flex items-center">
            <Image src={ceIcon} alt="CE-icon" className="w-28 h-28" />
            <div>
              <h1 className="font-extrabold text-4xl m-2">Conexão Estágios</h1>
              <h2 className="font-semibold text-[#787878]">
                Conectando talentos ao mundo profissional
              </h2>
            </div>
          </div>
          <div className="flex items-center mt-10">
            {!isRendering ? (
              <>
                <Button
                  styles="m-2 flex flex-col bg-[#0856D3] p-10 text-white text-3xl font-bold rounded-lg shadow-md hover:bg-[#0A6BFF] transition duration-300 ease-in-out w-full"
                  onClick={navigateLogin}
                >
                  Login
                  <span className="block text-sm font-light mt-1">
                    Já tem cadastro
                  </span>
                </Button>
                <Button
                  onClick={() => setRendering(true)}
                  styles="m-2 flex flex-col bg-[#0856D3] p-10 text-white text-3xl font-bold rounded-lg shadow-md hover:bg-[#0A6BFF] transition duration-300 ease-in-out w-full"
                >
                  Cadastro
                  <span className="block text-sm font-light mt-1">
                    Crie sua conta
                  </span>
                </Button>
              </>
            ) : (
              <>
                <Button
                  styles="m-2 flex flex-col bg-[#0856D3] p-10 text-white text-3xl font-bold rounded-lg shadow-md hover:bg-[#0A6BFF] transition duration-300 ease-in-out w-full"
                  onClick={navigateStudent}
                >
                  Estagiário
                  <span className="block text-sm font-light mt-1">
                    Encontre vagas
                  </span>
                </Button>
                <Button
                  styles="m-2 flex flex-col bg-[#0856D3] p-10 text-white text-3xl font-bold rounded-lg shadow-md hover:bg-[#0A6BFF] transition duration-300 ease-in-out w-full"
                  onClick={navigateCompany}
                >
                  Empresa
                  <span className="block text-sm font-light mt-1">
                    Contrate estágiarios
                  </span>
                </Button>
              </>
            )}
          </div>
        </div>
      </main>
    </div>
  );
}

export default Home;
