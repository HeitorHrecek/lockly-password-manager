/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ['./src/**/*.{html,css,ts}'],
  theme: {
    extend: {
      colors: {
        'custom-gray-292929': '#292929',
        'custom-gray-3F3F3F': '#3F3F3F'
      }
    },
  },
  plugins: [],
}
