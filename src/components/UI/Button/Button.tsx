"use client";
// Botões primário e secundários.
interface ButtonProps {
  children: React.ReactNode;
  onClick?: (event: React.MouseEvent) => void;
  disabled?: boolean;
  type?: "button" | "submit" | "reset";
  styles: string;
}

function Button({
  children,
  onClick,
  disabled = false,
  styles,
  type = "button",
}: ButtonProps) {
  return (
    <button
      type={type}
      className={styles}
      onClick={onClick}
      disabled={disabled}
    >
      {children}
    </button>
  );
}
export default Button;
