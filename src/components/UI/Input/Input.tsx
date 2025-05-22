"use client";
interface InputProps {
  children: React.ReactNode;
  id: string;
  type?: string;
  placeholder?: string;
  styles: string;
  stylesInput?: string;
  stylesLabel?: string;
}

function Input({
  id,
  type = "text",
  placeholder,
  children,
  styles,
  stylesInput,
  stylesLabel,
}: InputProps) {
  return (
    <div className={styles}>
      <label htmlFor={id} className={stylesLabel}>
        {children}
      </label>
      <input
        type={type}
        id={id}
        placeholder={placeholder}
        className={stylesInput}
        required
      />
    </div>
  );
}

export default Input;
