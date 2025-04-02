import type { Ranking } from '~/types/Ranking';

export default defineEventHandler(async (event) => {
  const config = useRuntimeConfig();
  const query = getQuery(event);
  const rankingType = query.rankingType;
  const page = query.page;
  const perPage = query.perPage;

  if (rankingType === undefined) {
    throw createError({
      statusCode: 400,
    });
  }

  const request = `/v1/ranking/real-time/${rankingType}-list?page=${page}&perPage=${perPage}`;

  const { data } = await $fetch<Api.Ranking.GetResponse<Ranking>>(request, {
    baseURL: config.public.apiBaseURL,
  });

  return data;
});
