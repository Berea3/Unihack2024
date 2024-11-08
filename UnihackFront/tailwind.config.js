/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["./src/**/*.{html,js}"],
  theme: {
    extend: {
        colors: {
            primary: '#E63946', // Red
            complementary: '#2A9D8F', // Green
            neutral: '#F1FAEE', // Light Gray
            accent: '#1D3557', // Dark Blue
            secondary: '#A8DADC', // Light Blue
        }
    },
  },
  plugins: [
    require('daisyui'),
  ],
}

