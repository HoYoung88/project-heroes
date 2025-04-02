<script setup lang="ts">
  const { label, value } = defineProps<{ label: string; value: string | number | undefined }>();
</script>

<template>
  <p class="text-base text-gray-600">{{ label }}: {{ value }}</p>
</template>

<style scoped></style>
