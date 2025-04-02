<script setup lang="ts">
  import type { NuxtError } from '#app';
  const { error } = defineProps<{ error: NuxtError }>();
  const handleError = () => clearError({ redirect: '/' });
</script>

<template>
  <div class="flex h-screen flex-col items-center justify-center bg-gray-100">
    <h1>{{ error.statusCode }}</h1>
    <h1>{{ error.message }}</h1>
    <button @click="handleError">Clear errors</button>
  </div>
</template>

<style scoped></style>
