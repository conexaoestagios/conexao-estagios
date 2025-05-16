// components/UI/Button.tsx
"use client";
// Botões reutilizáveis
interface ButtonProps {
  title: string;
  subtitle?: string;
  onClick: () => void;
  variant?: "start" | "continue";
  type?: "button";
}

export function Button({
  title,
  subtitle,
  onClick,
  variant = "start",
  type = "button",
}: ButtonProps) {
  const baseStyle =
    "text-white font-bold rounded-xl px-10 transition-colors mx-auto w-1/2 text-3xl flex flex-col items-center";

  const variantStyles = {
    start: "bg-blue-600 hover:bg-blue-700 py-6 cursor-pointer",
    continue:
      "bg-cyan-600 hover:bg-cyan-500 py-3 text-lg w-full cursor-pointer",
  };

  return (
    <button
      className={`${baseStyle} ${variantStyles[variant]}`}
      onClick={onClick}
      type={type}
    >
      {title}
      {subtitle && (
        <span className="block text-sm font-normal mt-1 text-xl">
          {subtitle}
        </span>
      )}
    </button>
  );
}
