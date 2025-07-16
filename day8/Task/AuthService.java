


    public JwtResponse authenticate(RegisterDetails registerDetails) {
        Authentication auth=AuthenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(registerDetails.getUsername(),registerDetails.getPassword())
        );
        String token=jwtTokenProvider.generateToken(auth);
        String username=registerDetails.getUsername();
        List<String> roles=Authentication.getAuthorities().stream()
                .map(role->role.getAuthority())
                .collect(Collectors.toList());
        String joinedRoles=String.join(",",roles);
        return new JwtResponse(token,username,joinedRoles);

    }
