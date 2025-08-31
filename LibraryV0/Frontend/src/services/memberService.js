import { api } from "./api";

export const getAllMembers = async () => {
  const response = await api.get("/members");
  return response.data;
};

export const getMemberByUserId = async (id) => {
  const response = await api.get(`/members/${id}`);
  return response.data;
};
