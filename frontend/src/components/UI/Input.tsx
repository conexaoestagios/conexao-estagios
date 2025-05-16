interface InputProps {
    label: string
    id: string
    type?: string
    placeholder?: string
}

export function Input({ label, id, type = 'text', placeholder }: InputProps) {
    return(
        <div>
            <label htmlFor={id} className="block text-sm font-semibold mb-2">{label}</label>
            <input
                type={type}
                id={id}
                placeholder={placeholder}
                className="w-full bg-white px-4 py-2 rounded-md text-gray-800 focus:ring-2 focus:ring-cyan-500 focus:outline-none"
            />
        </div>
    )
}