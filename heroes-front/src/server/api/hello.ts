export default defineEventHandler(() => {
  throw createError({
    status: 500,
    statusMessage: 'ERROR',
  });
  // return {
  //   aa: 'sdadsa',
  // };
});
