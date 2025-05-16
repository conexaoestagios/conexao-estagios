"use client";

import Image from "next/image";

interface HeaderProps {
  variant?: "center" | "side";
  title: string;
  subtitle?: string;
  backgroundImage?: string;
  logoSrc?: string;
  altLogo?: string;
}

export function Header({
  variant = "center",
  title,
  subtitle,
  backgroundImage,
  logoSrc,
  altLogo = "Logo",
}: HeaderProps) {
  const bgStyle =
    variant === "side" && backgroundImage
      ? {
          backgroundImage: `url(${backgroundImage})`,
          backgroundSize: "cover",
          backgroundPosition: "center",
        }
      : undefined;

  if (variant === "center") {
    return (
      <div>
        <div className="flex items-center gap-4 mb-2">
          {logoSrc && (
            <Image
              src={logoSrc}
              alt={altLogo}
              className="w-30 h-30"
              width={120}
              height={120}
            />
          )}
          <h1 className="text-3xl font-extrabold text-black [font-family:'Poppins-Bold',Helvetica] font-bold text-black text-5xl tracking-[0] leading-[normal]">
            {title}
          </h1>
        </div>
        {subtitle && (
          <p className="text-gray-500 text-sm [font-family:'Poppins-Bold',Helvetica] font-bold text-[#787878] text-[25px] tracking-[0] leading-[normal] text-xl">
            {subtitle}
          </p>
        )}
      </div>
    );
  }

  return (
    <div
      className="w-1/2 flex flex-col items-center justify-center p-8 bg-gray-50"
      style={bgStyle}
    >
      <div className="text-center mb-10">
        {logoSrc && (
          <Image
            src={logoSrc}
            alt={altLogo}
            className="w-25 mx-auto mb-6"
            width={100}
            height={100}
          />
        )}
        <h1 className="text-5xl font-bold text-gray-800 mb-2">{title}</h1>
        {subtitle && <h2 className="text-2xl text-gray-600">{subtitle}</h2>}
        {subtitle && (
          <p className="text-gray-500 mt-2 text-lg">
            {/* Mantive seu texto original no side? Ou esse p era só no center? */}
          </p>
        )}
      </div>
      <div className="mt-10"></div>
    </div>
  );
}
