<script setup lang="ts">
  defineProps<{ text: string; href: string }>();
</script>

<template>
  <li class="px-3 max-lg:border-b max-lg:py-3">
    <NuxtLink
      :to="href"
      class="block text-[15px] text-gray-500 lg:hover:text-[#007bff]"
    >
      {{ text }}
    </NuxtLink>
  </li>
</template>

<style scoped>
  .router-link-active {
    @apply text-[#007bff];
  }
</style>
