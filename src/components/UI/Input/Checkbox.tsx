// Checkbox para marcação e verificação
interface CheckboxProps {
  label: string;
  styleLabel?: string;
}

export function Checkbox({ label, styleLabel }: CheckboxProps) {
  return (
    <label className={styleLabel}>
      <input
        type="checkbox"
        className="h-4 w-4 accent-cyan-600 focus:ring-cyan-500"
      />
      {label}
    </label>
  );
}
