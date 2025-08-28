import { api } from "./api";

export const MemberService = {
  getAllMembers: async () => {
    const response = await api.get("/members");
    return response.data;
  },
  getMemberById: async (id) => {
    const response = await api.get(`/members/${id}`);
    return response.data;
  },
};
