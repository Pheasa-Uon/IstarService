SYSTEM FLOW STRUCTURE OVERVIEW
┌────────────┐       ┌────────────┐       ┌────────────┐
│   Users    │──────▶│   Roles    │──────▶│ Permissions│
└────┬───────┘       └────┬───────┘       └────┬───────┘
│                   └────────────┐        │
▼                                ▼        ▼
┌────────────┐                ┌────────────┐┌──────────────┐
│ UserProfile│                │   Features ││ RoleFeature  │
└────────────┘                └────────────┘└──────────────┘

SPRING BOOT BACKEND STRUCTURE
com.yourapp
├── config
│   └── SecurityConfig.java
├── controller
│   ├── AuthController.java
│   ├── UserController.java
│   └── RolePermissionController.java
├── dto
│   ├── LoginRequest.java
│   ├── JwtResponse.java
│   └── RoleFeaturePermissionDto.java
├── entity
│   ├── User.java
│   ├── UserProfile.java
│   ├── Role.java
│   ├── Feature.java
│   └── RoleFeaturePermission.java
├── repository
│   ├── UserRepository.java
│   ├── RoleRepository.java
│   ├── FeatureRepository.java
│   └── RoleFeaturePermissionRepository.java
├── security
│   ├── JwtUtils.java
│   ├── JwtFilter.java
│   └── CustomUserDetailsService.java
├── service
│   ├── AuthService.java
│   ├── UserService.java
│   └── RolePermissionService.java
└── YourAppApplication.java

