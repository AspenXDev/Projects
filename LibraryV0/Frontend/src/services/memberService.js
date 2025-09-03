// path: Frontend/src/services/MemberService.js
import { api } from "./api.js";

const getAuthHeader = () => ({
  Authorization: `Bearer ${localStorage.getItem("token")}`,
});

// --------------------
// Members
// --------------------
export const getMyMemberInfo = async () => {
  const response = await api.get("/members/my", { headers: getAuthHeader() });
  return response.data;
};

export const getAllMembers = async () => {
  const response = await api.get("/members", { headers: getAuthHeader() });
  return response.data;
};

export const getMemberById = async (memberId) => {
  const response = await api.get(`/members/${memberId}`, {
    headers: getAuthHeader(),
  });
  return response.data;
};

export const createMember = async (memberData) => {
  const response = await api.post("/members", memberData, {
    headers: getAuthHeader(),
  });
  return response.data;
};

export const updateMember = async (memberId, memberData) => {
  const response = await api.put(`/members/${memberId}`, memberData, {
    headers: getAuthHeader(),
  });
  return response.data;
};

export const deleteMember = async (memberId) => {
  const response = await api.delete(`/members/${memberId}`, {
    headers: getAuthHeader(),
  });
  return response.data;
};
