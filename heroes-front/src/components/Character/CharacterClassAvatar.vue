<script setup lang="ts">
  const { characterClassName } = defineProps<{ characterClassName: string | undefined }>();
  const imageSrc = computed(() => `/images/character/${characterClassName}.png`);
</script>

<template>
  <BaseAvatar
    class="h-16 w-16"
    :src="imageSrc"
  />
</template>

<style scoped></style>
