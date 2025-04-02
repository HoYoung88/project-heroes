<script setup lang="ts">
  const { src } = defineProps<{ src: string }>();
</script>

<template>
  <img
    class="h-10 w-10 rounded-full border border-gray-300"
    :src="src"
    alt="image avatar"
  />
</template>

<style scoped></style>
