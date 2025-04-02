export default defineEventHandler(async (event) => {
  const config = useRuntimeConfig();

  const { data } = await $fetch<Api.Meta.GetResponse<{ data: Record<string, unknown> }>>(
    `${config.public.apiBaseURL}/v1/meta-data`,
  );

  return data;
});
