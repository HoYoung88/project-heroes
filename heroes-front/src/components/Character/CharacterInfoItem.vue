<script setup lang="ts">
  const { label, value } = defineProps<{ label: string; value: string | number }>();
</script>

<template>
  <p class="text-base text-gray-800">{{ label }}: {{ value }}</p>
</template>

<style scoped></style>
