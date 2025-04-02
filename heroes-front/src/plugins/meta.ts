export default defineNuxtPlugin(async () => {
  const { data } = await useFetch(`/api/meta`);
  return {
    provide: {
      meta: data.value,
    },
  };
});
