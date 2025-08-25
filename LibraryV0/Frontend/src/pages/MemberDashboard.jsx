// src/pages/MemberDashboard.jsx
import React, { useEffect, useState } from "react";
import { useAuth } from "../contexts/AuthContext";
import { Navbar } from "../components/Navbar";
import * as LoanService from "../services/LoanService";
import * as ReservationService from "../services/ReservationService";
import * as FineService from "../services/FineService";
import * as UserService from "../services/UserService";

export function MemberDashboard() {
  const { user } = useAuth();

  const [dashboard, setDashboard] = useState({
    loans: [],
    reservations: [],
    fines: [],
    userInfo: {},
  });
  const [loading, setLoading] = useState(true);

  const [panels, setPanels] = useState({
    membership: true,
    loans: true,
    reservations: true,
    fines: true,
  });

  const fetchDashboardData = async () => {
    if (!user?.user_id) return;
    setLoading(true);
    try {
      const [loansData, reservationsData, finesData, userData] =
        await Promise.all([
          LoanService.getLoansByMember(user.user_id),
          ReservationService.getReservationsByMember(user.user_id),
          FineService.getFinesByMember(user.user_id),
          UserService.getMemberInfo(user.user_id),
        ]);

      setDashboard({
        loans: loansData || [],
        reservations: reservationsData || [],
        fines: finesData || [],
        userInfo: userData || {},
      });
    } catch (err) {
      console.error("Error fetching dashboard data:", err);
      setDashboard({
        loans: [],
        reservations: [],
        fines: [],
        userInfo: {},
      });
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchDashboardData();
  }, [user?.user_id]);

  const formatDate = (dateStr) =>
    dateStr ? new Date(dateStr).toLocaleDateString() : "-";

  const Panel = ({ title, children, isOpen, toggle }) => (
    <div
      style={{ marginBottom: 20, border: "1px solid #ccc", borderRadius: 4 }}
    >
      <div
        onClick={toggle}
        style={{
          cursor: "pointer",
          padding: 10,
          backgroundColor: "#f0f0f0",
          fontWeight: "bold",
        }}
      >
        {title} {isOpen ? "▲" : "▼"}
      </div>
      {isOpen && <div style={{ padding: 10 }}>{children}</div>}
    </div>
  );

  if (!user) return <p>Loading user info...</p>;

  return (
    <div>
      <Navbar currentPage="Member Dashboard" />
      <div style={{ padding: 20 }}>
        <h3>
          Welcome, <strong>{user.username}</strong>!
        </h3>

        <button
          onClick={fetchDashboardData}
          disabled={loading}
          style={{ marginBottom: 20 }}
        >
          {loading ? "Refreshing..." : "Refresh Dashboard"}
        </button>

        <Panel
          title="Membership Info"
          isOpen={panels.membership}
          toggle={() => setPanels((p) => ({ ...p, membership: !p.membership }))}
        >
          <p>Full Name: {dashboard.userInfo.full_name || "-"}</p>
          <p>
            Registration Date:{" "}
            {formatDate(dashboard.userInfo.registration_date)}
          </p>
          <p>
            Membership Valid Until:{" "}
            {formatDate(dashboard.userInfo.membership_valid_until)}
          </p>
          <p>Status: {dashboard.userInfo.membership_status || "-"}</p>
        </Panel>

        <Panel
          title="Active Loans"
          isOpen={panels.loans}
          toggle={() => setPanels((p) => ({ ...p, loans: !p.loans }))}
        >
          {dashboard.loans.length === 0 ? (
            <p>No active loans</p>
          ) : (
            <ul>
              {dashboard.loans.map((loan) => (
                <li key={loan.loan_id}>
                  {loan.bookTitle} — Due: {formatDate(loan.due_date)}
                </li>
              ))}
            </ul>
          )}
        </Panel>

        <Panel
          title="Reservations"
          isOpen={panels.reservations}
          toggle={() =>
            setPanels((p) => ({ ...p, reservations: !p.reservations }))
          }
        >
          {dashboard.reservations.length === 0 ? (
            <p>No active reservations</p>
          ) : (
            <ul>
              {dashboard.reservations.map((res) => (
                <li key={res.reservation_id}>
                  {res.bookTitle} — Status: {res.status}
                </li>
              ))}
            </ul>
          )}
        </Panel>

        <Panel
          title="Fines"
          isOpen={panels.fines}
          toggle={() => setPanels((p) => ({ ...p, fines: !p.fines }))}
        >
          {dashboard.fines.length === 0 ? (
            <p>No fines due</p>
          ) : (
            <ul>
              {dashboard.fines.map((fine) => (
                <li key={fine.fine_id}>
                  Amount: ${fine.amount?.toFixed(2) || "0.00"} — Paid:{" "}
                  {fine.paid ? "Yes" : "No"}
                </li>
              ))}
            </ul>
          )}
        </Panel>
      </div>
    </div>
  );
}
