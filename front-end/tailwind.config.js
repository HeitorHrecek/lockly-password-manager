/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/**/*.{html,ts}"
  ],
  theme: {
    extend: {
      colors: {
        primary: '#292929', 
        secondary: '#3F3F3F', 
        accent: '#FF5733',
        lightGray: '#E5E7EB',
        darkGray: '#1F2937',
        error: '#E74C3C',
        success: '#2ECC71',
        white: '#FFFFFF', 
        lightGray2: '#4E4D4D',
        'custom-gray-292929': '#292929',
        'custom-gray-3F3F3F': '#3F3F3F',
        'custom-gray-606060': '#606060',
        'custom-gray-646464': '#646464',
      }
    },
  },
  plugins: [],
}
