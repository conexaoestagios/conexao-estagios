"use client";
import React from "react";
import { useSearchParams } from "next/navigation";
import Form from "@/components/UI/Form/Form";

function Navigate() {
  const searchParams = useSearchParams();
  const variant = searchParams.get("variant");
  if (variant === "login") {
    return <Form variant="login" />;
  }
  if (variant === "company") {
    return <Form variant="company" />;
  }
  if (variant === "student") {
    return <Form variant="student" />;
  }
  return (
    <div className="flex flex-col items-center justify-center h-screen">
      <h1 className="text-2xl font-bold">Página não encontrada!</h1>
    </div>
  );
}

export default Navigate;
