// Checkbox para marcação e verificação
interface CheckboxProps {
  label: string;
}

export function Checkbox({ label }: CheckboxProps) {
  return (
    <label className="flex items-center gap-2">
      <input
        type="checkbox"
        className="h-4 w-4 accent-cyan-600 focus:ring-cyan-500"
      />
      {label}
    </label>
  );
}
