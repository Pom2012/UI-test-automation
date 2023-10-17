package com.model.base;

public interface Constants {

    String IC = "Innovation Center";
    String[] modelRoles = {
            "Model Participant",
            "Model User",
            "Participant",
            "Reports Viewer",
            "User",
            "Help Desk"
    };
    String[] modelRolesAcronym = {
            "MP",
            "MU",
            "P",
            "RV",
            "User",
            "HD"
    };

    interface AccountCreationData {
        String firstname = "CmsTestAdminOneFirst",
                lastname = "CmsTestAdminOneLast",
                birthmonth = "November",
                birthdate = "20",
                birthyear = "1969",
                zipcode = "21207";
    }

    interface IC_Roles {
        String IC_USER = "Innovation Center User",
                POI_USER = "Innovation Center Portal Operational Intelligence User",
                PV_IC_USER = "Innovation Center User + Innovation Center Privileged User",
                PV_USER = "Innovation Center Privileged User",
                APPROVER = "Innovation Center Application Approver",
                ADMIN = "Innovation Center Administrator",
                ADMIN_HD = "Innovation Center Administrator + Innovation Center Helpdesk User",
                HELPDESK = "Innovation Center Helpdesk User",
                HD_ADMIN = "Innovation Center Helpdesk Administrator",
                REPORT_USER = "Innovation Center Report User",
                PV_REPORT_USER = "Innovation Center Report User + Innovation Center Privileged User",
                BO = "Innovation Center Business Owner";
    }

    interface IC_Servlets {
        String AdmCon = "Administration Console",
                AppCon = "Application Console",
                RepCen = "Report Center",
                HDCen = "Support Center",
                UserVer = "User Verification",
                ListMngm = "List Management";
    }

    interface HDTabs {
        String SrchUs = "Search Users",
                BtchOp = "Batch Operations",
                AsnRole = "Assign Role",
                UsProf = "User Profile",
                WrnSt = "Warning State",
                EmSnd = "Email Sender",
                EmLog = "Email Log";
    }

    interface ModelApplications {
        String
                AHC = "Accountable Health Communities (AHC)",
                BPCI = "Bundled Payments for Care Improvement (BPCI) Advanced",
                CJR = "Comprehensive Care for Joint Replacement (CJR)",
                CDX = "CMMI Centralized Data Exchange (CDX)",
                CPC = "Comprehensive Primary Care Plus (CPC+)",
                CPCBIR = "Comprehensive Primary Care BI Reporting(CPCBIR)",
                ET3 = "Emergency Triage, Treat, and Transport (ET3)",
                eDFR = "Expanded Data Feedback Reporting (eDFR)",
                HDR = "Health Data Reporting (HDR)",
                HHVBP = "Home Health Value Based Purchasing (HHVBP)",
                ICBIR = "Innovation Center Business Intelligence Reports (ICBIR)",
                IPC = "Innovation Payment Contractor Portal (IPC Portal)",
                MDPCPBIR = "Maryland Primary Care Program Business Intelligence Reporting (MDPCPBIR)",
                MDPCP = "Maryland Primary Care Program-  MDPCP",
                MOM = "Maternal Opioid Misuse (MOM)",
                MH = "Million Hearts Data Registry (MH)",
                OCM = "Oncology Care Model (OCM)",
                OCMPlus = "Oncology Care Model Plus (OCM+)",
                EnhMTM = "Part D Enhanced Medication Therapy Management Model Application (Enh. MTM)",
                PCF = "Primary Care First (PCF)/Seriously Ill Population (SIP)",
                RO = "Radiation Oncology",
                RFAA = "Reusable Framework Administrative Application",

        /**
         * Dummy applications for testing purpose
         **/

        ONYD = "Old New Year Display",
                PCF_Dummy = "PCF_Dummy",
                AMRVT = "AMR Verification Testing",
                ARGO = "Argonauts",
                PERS = "Perseverance";
    }

    interface IC_Reports {
        String ADM_ACT = "Administrator Activity",
                LIST_ACT = "List Activity",
                APP_MONTR = "Application Monitoring",
                LAST_ACCESS = "Last Accessed",
                PV_USR = "Privileged User",
                USER_ACCS = "User Access",
                APP_SUM = "Application Summary",
                USRDETS = "User Role Details for Application",
                APP_USG = "Application Usage",
                CUS_ATTR = "Custom Attribute",
                US_INACT = "User Inactivity",
                US_VERIF = "User Verification",
                UsDet4App = "User Role Details for Application",
                UVNPU = "User Verification - Non Participant Users";
    }

    interface RequestStatus {
        String appr = "APPROVED",
                rej = "REJECTED",
                pend = "PENDING";
    }

    interface pages {
        String UVSch = "User Verification Schedule";
        String UVAsUs = "Associated Users";
    }

    interface UIControlType {
        String tBox = "Text Box",
                dDown = "Drop Down",
                rText = "Restricted Text",
                filText = "Filtered Text",
                ref = "Reference";
    }

    enum ListStatus {
        Enabled,
        Disabled,
        Terminated
    }

    interface ApplicationConsoleTabs {
        String appsTab = "Applications",
                reqAccTab = "Request Access",
                myReqs = "My Requests",
                confAccTab = "Confirm Access",
                delAccTab = "Delegate Access",
                emailsTab = "EmailNotifications";
    }

    interface RequestStatusTabs {
        String allTab = "All",
                apprTab = "Approved",
                rejTab = "Rejected",
                pendTab = "Pending";
    }

    interface userInactiveMessage {
        String msgForUserInactWrn = "Hi ${firstName}, " +
                "\nYou haven’t logged into the ${applicationName} for ${inactiveDaysStr} days. " +
                "\nTo comply with CMS security policies, we’ll automatically remove your role(s) in ${toTermDaysStr} day(s). " +
                "\n\nIf you need to retain your access, log into the application today! " +
                "\nIf you have any questions or concerns, ${applicationName} " +
                "\n\nSupport can be reached directly at 1-888-372-3280 or by email at helpdesk@perseverance.com." +
                "\n\nThank you, " +
                "\n${applicationName} + Support";

        String msgForUserInactRejWrn = "Hi ${firstName}, " +
                "\nWe noticed that you haven’t logged into the ${applicationName} in the last 60 days. " +
                "\nTo keep our system safe, we removed your role(s) because of the inactivity. " +
                "\nIf you require your access to be reinstated, please have the practice(s) point of contact " +
                "email helpdesk@perseverance.com to have your access reauthorized." +
                "\n\nThank you, " +
                "\n${applicationName} + Support";
    }


    String AdmCenMod = "Manage Applications";
    String RepCenBanner = "CMMI Report";
    String ListModule = "List Management of Application Custom Attributes";
    String HDMod = "CMMI Helpdesk";
    String AppConsMod = "CMMI";
    String UVMod = "User Verification Schedule";

    String ENABLED = "enabled";
    String DISABLED = "disabled";
    String VISIBLE = "visible";
    String INVISIBLE = "hidden";
    String REPORTS_FOLDER = "reports";
    String INPUT = "INPUT";
    String SELECT = "SELECT";
    String DIV = "DIV";
    String TEXTAREA = "TEXTAREA";
    String ACTUAL = ". Actual : |";
    String EXPECTED = "|Expected : |";

    interface fields {
        String UID = "User ID";
        String FN = "First Name";
        String LN = "Last Name";
        String RID = "Request ID";
        String AN = "Application Name";
        String R = "Role";
        String ARN = "Application Role Name";
        String CA = "Custom Attribute";
        String RD = "Requested Date";
        String AAD = "Assigned/Approved Date";
        String STS = "Status";
        String JST = "Justification";
        String EmAdr = "Email Address";
    }

}
