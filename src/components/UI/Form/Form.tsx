// Importando elementos visuais.
import Image from "next/image";
import bgStudent from "../../../assets/register-st-background.png";
import bgCompany from "../../../assets/register-co-background.png";
import bgLogin from "../../../assets/main-background.png";
import ceIcon from "../../../assets/ce-icon.svg";
import arrowIcon from "../../../assets/arrow-icon.png";
import Button from "../Button/Button";
import Input from "../Input/Input";
import { Checkbox } from "../Input/Checkbox";
import { useRouter } from "next/navigation";

interface FormProps {
  variant?: "login" | "student" | "company";
}
function Form({ variant }: FormProps) {
  const router = useRouter();
  if (variant === "student") {
    return (
      <div className="flex w-screen h-screen ">
        <div
          className="flex-1 bg-white text-white items-center justify-center text-center"
          style={{
            backgroundImage: `url(${bgStudent.src})`,
            backgroundSize: "cover",
            backgroundPosition: "center",
            height: "auto",
            width: "100%",
          }}
        >
          <header className="flex flex-col w-full h-full items-center justify-center text-center">
            <h1 className="font-extrabold text-4xl m-2 text-black">
              Estagiário
            </h1>
            <p className="font-semibold text-[#8e8e8e] text-2xl  ">
              Encontre oportunidades no mercado de trabalho
            </p>
          </header>
        </div>
        <div className="flex-1 bg-blue items-center">
          <form className="flex flex-col w-full h-full bg-[#0f333e] items-center p-6 ">
            <nav className=" flex justify-between w-full items-center mb-12">
              <h1 className="font-bold text-4xl text-white mt-5 ml-20">
                Cadastro
              </h1>
              <Button
                styles="bg-[#3BBB18] w-12 h-12 mt-5 mr-20 rounded-lg p-0 flex items-center justify-center "
                onClick={(e) => {
                  e.preventDefault();
                  router.push("/");
                }}
              >
                <Image src={arrowIcon} alt="Arrow Icon" className="w-8 h-8" />
              </Button>
            </nav>
            <main className="max-w 2xl mx-auto grid grid-cols-2 gap-6">
              <div className="space-y-2">
                <Input
                  styles=""
                  stylesLabel="block text-white text-sm font-semibold mb-2"
                  stylesInput="w-full bg-white px-4 py-2 rounded-md text-gray-800 focus:ring-2 focus:ring-cyan-500 focus:outline-none"
                  placeholder="Digite o nome completo"
                  id="name"
                >
                  Nome completo
                </Input>
              </div>

              <div className="space-y-2">
                <Input
                  styles=""
                  stylesLabel="block text-white text-sm font-semibold mb-2"
                  stylesInput="w-full bg-white px-4 py-2 rounded-md text-gray-800 focus:ring-2 focus:ring-cyan-500 focus:outline-none"
                  placeholder="Digite o usuário"
                  id="username"
                >
                  Usuário
                </Input>
              </div>

              <div className="space-y-2">
                <Input
                  styles=""
                  stylesLabel="block text-white text-sm font-semibold mb-2"
                  stylesInput="w-full bg-white px-4 py-2 rounded-md text-gray-800 focus:ring-2 focus:ring-cyan-500 focus:outline-none"
                  placeholder="Digite o número de telefone"
                  id="phone"
                >
                  Telefone
                </Input>
              </div>

              <div className="space-y-2">
                <Input
                  styles=""
                  stylesLabel="block text-white text-sm font-semibold mb-2"
                  stylesInput="w-full bg-white px-4 py-2 rounded-md text-gray-800 focus:ring-2 focus:ring-cyan-500 focus:outline-none"
                  placeholder="Digite o CPF"
                  id="cpf"
                >
                  CPF
                </Input>
              </div>

              <div className="space-y-2">
                <Input
                  styles=""
                  stylesLabel="block text-white text-sm font-semibold mb-2"
                  stylesInput="w-full bg-white px-4 py-2 rounded-md text-gray-800 focus:ring-2 focus:ring-cyan-500 focus:outline-none"
                  placeholder="Digite a cidade"
                  id="city"
                >
                  Cidade
                </Input>
              </div>
              <div className="space-y-2 ">
                <Input
                  styles=""
                  stylesLabel="block text-white text-sm font-semibold mb-2"
                  stylesInput="w-full bg-white px-4 py-2 rounded-md text-gray-800 focus:ring-2 focus:ring-cyan-500 focus:outline-none"
                  placeholder="Digite o estado"
                  id="state"
                >
                  Estado
                </Input>
              </div>
              <div className="space-y-2  col-span-2">
                <Input
                  styles=""
                  stylesLabel="block text-white text-sm font-semibold mb-2"
                  stylesInput="w-full bg-white px-4 py-2 rounded-md text-gray-800 focus:ring-2 focus:ring-cyan-500 focus:outline-none"
                  placeholder="Digite o email"
                  id="email"
                >
                  Email
                </Input>
              </div>
              <div className="space-y-2">
                <Input
                  styles=""
                  stylesLabel="block text-white text-sm font-semibold mb-2"
                  stylesInput="w-full bg-white px-4 py-2 rounded-md text-gray-800 focus:ring-2 focus:ring-cyan-500 focus:outline-none"
                  placeholder="Digite sua senha"
                  id="password"
                >
                  Senha
                </Input>
              </div>
              <div className="space-y-2">
                <Input
                  styles=""
                  stylesLabel="block text-white text-sm font-semibold mb-2"
                  stylesInput="w-full bg-white px-4 py-2 rounded-md text-gray-800 focus:ring-2 focus:ring-cyan-500 focus:outline-none"
                  placeholder="Confirme a senha"
                  id="confirmpassword"
                >
                  Confirme a senha
                </Input>
              </div>
              <div className="space-y-2 col-span-2">
                <Button
                  type="submit"
                  styles=" bg-[#006989] p-6 text-white  py-3 text-lg w-full cursor-pointer text-xl font-bold rounded-lg shadow-md hover:bg-[#0A6BFF] transition duration-300 ease-in-out"
                >
                  Continuar
                </Button>
              </div>
            </main>
          </form>
        </div>
      </div>
    );
  }
  if (variant === "company") {
    return (
      <div className="flex w-screen h-screen ">
        <div
          className="flex-1 bg-white text-white items-center justify-center text-center"
          style={{
            backgroundImage: `url(${bgCompany.src})`,
            backgroundSize: "cover",
            backgroundPosition: "center",
            height: "auto",
            width: "100%",
          }}
        >
          <header className="flex flex-col w-full h-full items-center justify-center text-center">
            <h1 className="font-extrabold text-4xl m-2 text-black">Empresa</h1>
            <p className="font-semibold text-[#8e8e8e] text-2xl  ">
              Contrate novos talentos
            </p>
          </header>
        </div>
        <div className="flex-1 bg-blue items-center">
          <form className="flex flex-col w-full h-full bg-[#0f333e] items-center p-6 ">
            <nav className=" flex justify-between w-full items-center mb-12">
              <h1 className="font-bold text-4xl text-white mt-5 ml-20">
                Cadastro
              </h1>
              <Button
                styles="bg-[#3BBB18] w-12 h-12 mt-5 mr-20 rounded-lg p-0 flex items-center justify-center "
                onClick={(e) => {
                  e.preventDefault();
                  router.push("/");
                }}
              >
                <Image src={arrowIcon} alt="Arrow Icon" className="w-8 h-8" />
              </Button>
            </nav>
            <main className="max-w 2xl mx-auto grid grid-cols-2 gap-6">
              <div className="space-y-2">
                <Input
                  styles=""
                  stylesLabel="block text-white text-sm font-semibold mb-2"
                  stylesInput="w-full bg-white px-4 py-2 rounded-md text-gray-800 focus:ring-2 focus:ring-cyan-500 focus:outline-none"
                  placeholder="Digite nome fantasia"
                  id="fullname"
                >
                  Nome Fantasia
                </Input>
              </div>

              <div className="space-y-2">
                <Input
                  styles=""
                  stylesLabel="block text-white text-sm font-semibold mb-2"
                  stylesInput="w-full bg-white px-4 py-2 rounded-md text-gray-800 focus:ring-2 focus:ring-cyan-500 focus:outline-none"
                  placeholder="Digite a Razão Social"
                  id="companyname"
                >
                  Razão Social
                </Input>
              </div>

              <div className="space-y-2">
                <Input
                  styles=""
                  stylesLabel="block text-white text-sm font-semibold mb-2"
                  stylesInput="w-full bg-white px-4 py-2 rounded-md text-gray-800 focus:ring-2 focus:ring-cyan-500 focus:outline-none"
                  placeholder="Digite seu CNPJ"
                  id="cnpj"
                >
                  CNPJ
                </Input>
              </div>

              <div className="space-y-2">
                <Input
                  styles=""
                  stylesLabel="block text-white text-sm font-semibold mb-2"
                  stylesInput="w-full bg-white px-4 py-2 rounded-md text-gray-800 focus:ring-2 focus:ring-cyan-500 focus:outline-none"
                  placeholder="Digite o nome de usuário"
                  id="username"
                >
                  Nome de Usuário
                </Input>
              </div>

              <div className="space-y-2  col-span-2">
                <Input
                  styles=""
                  stylesLabel="block text-white text-sm font-semibold mb-2"
                  stylesInput="w-full bg-white px-4 py-2 rounded-md text-gray-800 focus:ring-2 focus:ring-cyan-500 focus:outline-none"
                  placeholder="Digite o email"
                  id="email"
                >
                  E-mail
                </Input>
              </div>
              <div className="space-y-2 ">
                <Input
                  styles=""
                  stylesLabel="block text-white text-sm font-semibold mb-2"
                  stylesInput="w-full bg-white px-4 py-2 rounded-md text-gray-800 focus:ring-2 focus:ring-cyan-500 focus:outline-none"
                  placeholder="Digite a senha"
                  id="password"
                >
                  Senha
                </Input>
              </div>
              <div className="space-y-2">
                <Input
                  styles=""
                  stylesLabel="block text-white text-sm font-semibold mb-2"
                  stylesInput="w-full bg-white px-4 py-2 rounded-md text-gray-800 focus:ring-2 focus:ring-cyan-500 focus:outline-none"
                  placeholder="Confirme a senha"
                  id="confirmpassword"
                >
                  Confirmar senha
                </Input>
              </div>
              <div className="space-y-2">
                <Input
                  styles=""
                  stylesLabel="block text-white text-sm font-semibold mb-2"
                  stylesInput="w-full bg-white px-4 py-2 rounded-md text-gray-800 focus:ring-2 focus:ring-cyan-500 focus:outline-none"
                  placeholder="Digite a cidade"
                  id="city"
                >
                  Cidade
                </Input>
              </div>
              <div className="space-y-2">
                <Input
                  styles=""
                  stylesLabel="block text-white text-sm font-semibold mb-2"
                  stylesInput="w-full bg-white px-4 py-2 rounded-md text-gray-800 focus:ring-2 focus:ring-cyan-500 focus:outline-none"
                  placeholder="Digite o estado"
                  id="state"
                >
                  Estado
                </Input>
              </div>
              <div className="space-y-2">
                <Checkbox
                  label=" Aceito os termos de uso."
                  styleLabel="text-white text-extralight text-sm whitespace-nowrap
"
                />
              </div>
              <div className="space-y-2 col-span-2">
                <Button
                  type="submit"
                  styles=" bg-[#006989] p-6 text-white  py-3 text-lg w-full cursor-pointer text-xl font-bold rounded-lg shadow-md hover:bg-[#0A6BFF] transition duration-300 ease-in-out"
                >
                  Continuar
                </Button>
              </div>
            </main>
          </form>
        </div>
      </div>
    );
  }

  return (
    <div
      className="flex min-h-screen"
      style={{
        backgroundImage: `url(${bgLogin.src})`,
        backgroundSize: "cover",
        backgroundPosition: "center",
        height: "auto",
        width: "100%",
      }}
    >
      <main className="flex items-center justify-center w-screen">
        <div className="flex flex-1 h-screen items-center justify-center">
          <Image src={ceIcon} alt="CE-Icon" className="w-28 h-28" />
          <div>
            <h1 className="font-extrabold text-4xl m-2">Conexão Estágios</h1>
            <h2 className="font-semibold text-[#787878]">
              Conectando talentos ao mundo profissional
            </h2>
          </div>
        </div>
        <form action="" className="flex flex-col flex-1 bg-[#0F343F] h-screen">
          <header className="flex justify-center justify-between">
            <h1 className="text-white font-bold text-4xl p-10">Login</h1>
            <Button
              styles="flex bg-[#3BBB18] w-14 h-14 m-9 text-center rounded-xl justify-center items-center"
              onClick={(e) => {
                e.preventDefault();
                router.push("/");
              }}
            >
              <Image src={arrowIcon} alt="Arrow Icon" className="w-8 h-8" />
            </Button>
          </header>
          <section className="flex flex-col items-center justify-center w-full h-screen bg-[#0F343F]">
            <Input
              styles="flex flex-col w-1/2"
              stylesLabel="block text-white text-sm font-semibold mb-2"
              id="username"
              stylesInput="w-full bg-white px-4 py-2 rounded-md text-gray-800 focus:ring-2 focus:ring-cyan-500 focus:outline-none"
              placeholder="Digite seu nome de usuário"
            >
              Usuário
            </Input>
            <Input
              styles="flex flex-col w-1/2"
              stylesLabel="block text-white text-sm font-semibold mb-2 mt-10"
              id="password"
              stylesInput="bg-white px-4 py-2 rounded-md text-gray-800 focus:ring-2 focus:ring-cyan-500 focus:outline-none"
              placeholder="Digite sua senha"
              type="password"
            >
              Senha
            </Input>

            <div className="flex justify-between m-4 w-full text-sm mt-10">
              <Checkbox
                label="Lembrar"
                styleLabel="ml-48 flex items-center gap-2 text-white"
              />
              <a
                href="#"
                className="mr-48 text-cyan-300 hover:text-cyan-100 hover:underline"
              >
                Esqueceu a senha?
              </a>
            </div>
            <Button
              type="submit"
              styles="w-1/2 mt-1 bg-[#006989] p-6 text-white text-xl font-bold rounded-lg shadow-md hover:bg-[#0A6BFF] transition duration-300 ease-in-out"
            >
              Continuar
            </Button>
            <p className="text-sm mt-8 text-center text-cyan-100">
              Ainda não criou sua conta no Conexão Estágios?{" "}
              <a
                href="#"
                className="font-semibold text-cyan-300 hover:text-white hover:underline"
              >
                Cadastre-se
              </a>
            </p>
          </section>
        </form>
      </main>
    </div>
  );
}

export default Form;
