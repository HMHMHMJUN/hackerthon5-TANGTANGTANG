package org.kernel360.tang.auth;

import lombok.RequiredArgsConstructor;
import org.kernel360.tang.auth.dto.LoginRequest;
import org.kernel360.tang.common.AppException;
import org.kernel360.tang.common.AuthExceptionCode;
import org.kernel360.tang.member.Member;
import org.kernel360.tang.member.MemberMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {
    private final MemberMapper memberMapper;
    private final PasswordEncoder passwordEncoder;

    public Member login(LoginRequest request) {
        Member member = memberMapper.selectMemberByUsername(request.getUsername());
        if (member == null || !passwordEncoder.matches(request.getPassword(), member.getPassword())) {
            throw new AppException(AuthExceptionCode.INVALID_LOGIN);
        }
        return member;
    }
}
