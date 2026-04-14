export const isPositiveNumber = (val) => {
  return !isNaN(val) && Number(val) > 0;
};