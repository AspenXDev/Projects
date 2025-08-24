import { Navigate } from "react-router-dom";
import { useContext } from "react";
import AuthContext from "../contexts/AuthContext";

const PrivateRoute = ({ roles, children }) => {
  const { user } = useContext(AuthContext);

  if (!user) return <Navigate to="/login" />;
  if (roles && !roles.includes(user.role)) return <Navigate to="/" />;
  return children;
};

export default PrivateRoute;
