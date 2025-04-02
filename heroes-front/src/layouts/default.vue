<script setup lang="ts"></script>

<template>
  <div class="flex min-h-screen flex-col">
    <HeaderArea />
    <main class="h-full w-full">
      <div class="mx-auto max-w-screen-xl py-8">
        <slot />
      </div>
    </main>

    <footer class="bg-white px-6 py-4 text-center text-sm text-gray-400 shadow">
      © 2025 게임 정보 제공 사이트
    </footer>
  </div>
</template>

<style scoped>
  main {
    /*
    @apply max-w-[1024px] min-w-[1024px];
    */

    min-height: calc(100vh - 65px - 52px);
  }
</style>
