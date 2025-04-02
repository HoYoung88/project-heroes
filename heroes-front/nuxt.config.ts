import tailwindcss from '@tailwindcss/vite';
// https://nuxt.com/docs/api/configuration/nuxt-config
export default defineNuxtConfig({
  compatibilityDate: '2024-11-01',
  devtools: { enabled: true },
  css: ['~/assets/css/main.css'],
  modules: ['@nuxt/eslint', 'nuxt-headlessui', '@pinia/nuxt'],
  vite: {
    plugins: [tailwindcss()],
  },
  srcDir: 'src/',
  app: {
    rootAttrs: {
      id: 'app',
    },
    head: {
      htmlAttrs: {
        lang: 'ko',
        class: 'bg-[#f7f6f9]',
      },
      bodyAttrs: {},
    },
  },
  headlessui: {
    prefix: 'Headless',
  },
  imports: {
    dirs: ['composables', 'composables/*/*.{ts,js,mjs,mts}', 'composables/**'],
  },
  runtimeConfig: {
    public: {
      // apiBaseURL: 'http://localhost:3100/api',
      apiBaseURL: 'http://localhost:8080',
    },
  },
  components: [{ path: '~/components/ToastUi', prefix: 'Tui' }, '~/components'],
});
